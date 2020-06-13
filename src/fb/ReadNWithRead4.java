package fb;

/*
Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.

Method read4:
The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
The return value is the number of actual characters read.
Note that read4() has its own file pointer, much like FILE *fp in C.

Definition of read4:
    Parameter:  char[] buf
    Returns:    int
Note: buf[] is destination not source, the results from read4 will be copied to buf[]

Below is a high level example of how read4 works:

File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
char[] buf = new char[4]; // Create buffer with enough space to store characters
read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file
 */

/*
1. maintain a char[4] array to as cache to store data read from read4()
2. maintain cacheIndex variable and cacheSize variable
3. if cache is empty, load cache with read4().
4. while index < n:
    if there are valid data in cache, use it.
    else, load cache with read4()
    if end of file is reached, break -> return.
*/
public class ReadNWithRead4 {
	public class Solution extends Reader4 {
		/**
		 * @param buf Destination buffer
		 * @param n   Number of characters to read
		 * @return The number of actual characters read
		 */
		private char[] cache = new char[4];
		private int cacheSize = 0;
		private int cacheIndex = 0;

		public int read(char[] buf, int n) {
			// if our cache is empty, load it
			if (cacheSize == 0) {
				cacheSize = read4(cache);
			}
			int index = 0;
			while (index < n) {
				// if we have data in cache, use it
				if (cacheIndex < cacheSize) {
					buf[index++] = cache[cacheIndex++];
				} else {
					// all data in cache were used, load new data into cache
					cacheSize = read4(cache);
					cacheIndex = 0;
				}
				// if end of file is reached, break
				if (cacheSize == 0) {
					break;
				}
			}
			return index;
		}
	}

	/*
	If it is not required to maintain the pointer, here is the simplified version
	 */
	public int read(char[] buf, int n) {
		char[] tmp = new char[4];
		int index = 0;
		while (index < n) {
			int readCount = read4(tmp);
			if (readCount == 0) {
				break;
			}
			for (int i = 0; i < readCount && index < n; i++) {
				buf[index++] = tmp[i];
			}
		}
		return index;
	}
}
