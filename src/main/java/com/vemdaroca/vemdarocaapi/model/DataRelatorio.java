package com.vemdaroca.vemdarocaapi.model;

import java.time.Instant;
import java.time.ZoneOffset;

public class DataRelatorio {

	Instant start;

	Instant end;

	public DataRelatorio() {
	}

	public DataRelatorio(Instant start, Instant end) {
		this.start = Instant.now();
		this.end = end;
	}

	public Instant getStart() {
		return start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}

	public Instant getEnd() {
		return end.atZone(ZoneOffset.UTC).withDayOfMonth(end.atOffset(ZoneOffset.UTC).getDayOfMonth() + 1).withHour(2)
				.withMinute(59).withSecond(59).toInstant();
	}

	public void setEnd(Instant end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "DataRelatorio [start=" + start + ", end=" + getEnd() + "]";
	}

}
