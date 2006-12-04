package org.freecompany.redline;

import org.freecompany.redline.header.Format;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class DumpPayload {

	public static void main( String[] args) throws Exception {
		ReadableByteChannel in = Channels.newChannel( System.in);
		Format format = new Scanner().run( new ReadableChannelWrapper( in));
		FileChannel out = new FileOutputStream( args[ 0]).getChannel();
		
		long position = 0;
		long read;
		while (( read = out.transferFrom( in, position, 1024)) > 0) position += read;
	}
}
