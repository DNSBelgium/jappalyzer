package com.vampbear.jappalyzer;

public class TechnologyMatch {

    public static final String HEADER = "header";

    private final Technology technology;
    private final long duration;
    private final String reason;

    public TechnologyMatch(Technology technology, String reason, long duration) {
        this.technology = technology;
        this.reason = reason;
        this.duration = duration;
    }

    public Technology getTechnology() {
        return technology;
    }

    public long getDuration() {
        return duration;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "TechnologyMatch{" +
                "technology=" + technology.getName() +
                ", reason=" + reason +
                ", duration=" + duration + "ms" +
                '}';
    }
}
