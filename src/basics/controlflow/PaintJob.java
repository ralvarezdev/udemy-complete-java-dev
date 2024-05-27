package basics.controlflow;

// Exercise 29
public class PaintJob {
	public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
		if (extraBuckets < 0 || width <= 0 || height <= 0 || areaPerBucket <= 0)
			return -1;

		double areaToCover = width * height;
		double areaBuckets = areaPerBucket * extraBuckets;

		return (int) Math.ceil((areaToCover - areaBuckets) / areaPerBucket);
	}

	public static int getBucketCount(double width, double height, double areaPerBucket) {
		if (width <= 0 || height <= 0 || areaPerBucket <= 0)
			return -1;

		double areaToCover = width * height;

		return (int) Math.ceil(areaToCover / areaPerBucket);
	}

	public static int getBucketCount(double area, double areaPerBucket) {
		if (area <= 0 || areaPerBucket <= 0)
			return -1;

		return (int) Math.ceil(area / areaPerBucket);
	}
}
