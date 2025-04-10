package org.example;

public class T2time {
    static final int T2DAYSECONDS = 13*3600+27*60+16;
    private int seconds = 0;

    /**
     * @param secs an integer representing the number of seconds since midnight
     * @inv this.seconds in 0..T2DAYSECONDS-1
     * @post this.seconds==secs
     * @throws IllegalArgumentException if secs not in 0..T2DAYSECONDS-1
     */
    public T2time(int secs) {
        if (secs < 0 || secs >= T2DAYSECONDS) {
            throw new IllegalArgumentException("0 <= secs < T2DAYSECONDS");
        }
        this.seconds = secs;
    }

    /**
     * @param h an integer representing the number of hours since midnight
     * @param m an integer representing the number of min since h
     * @param s an integer representing the number of hours since m
     * @inv this.seconds in 0..T2DAYSECONDS-1
     * @post this.seconds = h*3600 + m*60 + s
     * @throws IllegalArgumentException if (h*3600 + m*60 + s) not in [0, T2DAYSECONDS-1]
     */
    public T2time(int h, int m, int s) { this(h*3600 + m*60 + s); }

    /**
     * @param time a string representing the time in the format "HH:MM:SS"
     * @throws IllegalArgumentException if time is not in the format "HH:MM:SS"
     * @pre time != null
     * @inv this.seconds in 0..T2DAYSECONDS-1
     * @post this.seconds = h*3600 + m*60 + s
     */
    public T2time(String time) {
        String[] parts = time.split(":");
        if (parts.length != 3)
            throw new IllegalArgumentException("Invalid time format");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        this.seconds = h * 3600 + m * 60 + s;
    }

    public static boolean isTime(String time) {
        String[] parts = time.split(":");
        if (parts.length != 3) return false;
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h >= 0 && h < 24 && m >= 0 && m < 60 && s >= 0 && s < 60;
    }

    /**
     * @return this.seconds
     */
    public int asSeconds() { return seconds; }

    /**
     * @return this.seconds formatted as "HH:MM:SS"
     */
    public String toString() {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = (seconds % 3600) % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    /**
     * @pre arg != null
     * @return new T2time with the sum of this and arg mod T2DAYSECONDS
     * @post this == old this
     */
    public T2time add(T2time arg) {
        if (arg == null) {
            throw new IllegalArgumentException("arg != null");
        }
        int sum = (this.seconds + arg.asSeconds()) % T2DAYSECONDS;
        return new T2time(sum);
    }
}
