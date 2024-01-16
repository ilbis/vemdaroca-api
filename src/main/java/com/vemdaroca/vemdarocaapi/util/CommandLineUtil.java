package com.vemdaroca.vemdarocaapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.vemdaroca.vemdarocaapi.dto.CommandReturnDTO;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class CommandLineUtil {

	/**
	 * @param path    The path that command will be triggered
	 * @param command A bash command line
	 * @return CommandReturn
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public CommandReturnDTO executeCommandLine(String path, String command) throws IOException, InterruptedException {

		CommandReturnDTO cr = new CommandReturnDTO();
		final String BASH_PATH = "/bin/bash";

		int exitVal = -1;

		ProcessBuilder processBuilder = new ProcessBuilder(BASH_PATH, "-c", command);
		processBuilder.directory(new File(path));
		Process proc = processBuilder.start();

		CommandLineUtil.StreamGobbler errorGobbler = new CommandLineUtil.StreamGobbler(proc.getErrorStream(), "ERROR");
		CommandLineUtil.StreamGobbler outputGobbler = new CommandLineUtil.StreamGobbler(proc.getInputStream(),
				"OUTPUT");

		errorGobbler.start();
		outputGobbler.start();

		exitVal = proc.waitFor();
		log.info("ExitValue: " + exitVal);

		cr.setExitVal(exitVal);
		cr.setLogError(errorGobbler.getCommandlog().toString());

		return cr;
	}

	class StreamGobbler extends Thread {
		private InputStream is;
		private String type;
		public StringBuilder commandlog;

		StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
			this.commandlog = new StringBuilder();
		}

		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;

				while ((line = br.readLine()) != null) {
					String stringToAppend = String.format("%s > %s", type.replaceAll("[\r\n]", ""),
							line.replaceAll("[\r\n]", ""));
					log.info(stringToAppend);
					commandlog.append(stringToAppend + System.lineSeparator());
				}
			} catch (IOException ioe) {
				log.error("Error", ioe);
			}
		}

		public StringBuilder getCommandlog() {
			return commandlog;
		}
	}
}
