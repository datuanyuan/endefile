package com.datuanyuan.endefile;

public class ArrayUtils {
	
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
	
	/**
     * <p>Checks if an array of Objects is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive longs is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive ints is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive shorts is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive chars is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive bytes is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive doubles is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive floats is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>Checks if an array of primitive booleans is empty or {@code null}.</p>
     *
     * @param array  the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final boolean[] array) {
        return array == null || array.length == 0;
    }

	public static byte[] append(byte[] org, byte[] to) {
		byte[] newByte = new byte[org.length + to.length];
		System.arraycopy(org, 0, newByte, 0, org.length);
		System.arraycopy(to, 0, newByte, org.length, to.length);
		return newByte;
	}

	public static byte[] concat(byte[]... bytes) {
		int length = 0;
		byte[][] arrayOfByte1 = bytes;
		int j = bytes.length;
		for (int i = 0; i < j; i++) {
			byte[] b = arrayOfByte1[i];
			length += b.length;
		}
		byte[] newByte = new byte[length];

		byte[][] arrayOfByte2 = bytes;
		int k = bytes.length;
		for (j = 0; j < k; j++) {
			byte[] b = arrayOfByte2[j];
			newByte = append(newByte, b);
		}

		return newByte;
	}

    /**
     * 合并字节数组
     *
     * @param bytePre
     * @param byteEnd
     * @return
     */
    public static byte[] byteMerger(byte[] bytePre, byte[] byteEnd) {
        byte[] totalByte = new byte[bytePre.length + byteEnd.length];
        System.arraycopy(bytePre, 0, totalByte, 0, bytePre.length);
        System.arraycopy(byteEnd, 0, totalByte, bytePre.length, byteEnd.length);
        return totalByte;
    }

    /**
     * 在一个字节数组中查找另个字节数组，并返回起始下标
     *
     * @param source
     * @param search
     * @return
     */
    public static int searchInByteArray(byte[] source, byte[] search) {
        if (source == null || search == null || source.length == 0 || search.length == 0 || source.length < search.length) {
            return -1;
        }
        int i, j;
        for (i = 0; i < source.length - search.length + 1; i++) {
            if (source[i] == search[0]) {
                for (j = 1; j < search.length; j++) {
                    if (source[i + j] != search[j]) break;
                }
                if (j == search.length) return i;
            }
        }
        return -1;
    }
}
