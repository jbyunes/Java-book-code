package chapter.threads.parallel.streams;

public record Result(long dataLength, long pipeLength,
    long sequential, long parallel, double acceleration) {}

