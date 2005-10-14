package org.freecompany.redline;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

class Util {
	private Util() {}

	static ByteBuffer fill( ReadableByteChannel in, int size) throws IOException {
		return fill( in, ByteBuffer.allocate( size));
	}

	static ByteBuffer fill( ReadableByteChannel in, ByteBuffer buffer) throws IOException {
		while ( buffer.hasRemaining()) in.read( buffer);
		buffer.flip();
		return buffer;
	}

	static void empty( WritableByteChannel out, ByteBuffer buffer) throws IOException {
		while ( buffer.hasRemaining()) out.write( buffer);
	}

	static void check( int expected, int actual) throws IOException {
		if ( expected != actual) System.err.println( "check expected " + Integer.toHexString( expected) + ", found " + Integer.toHexString( actual));
	}

	static void check( byte expected, byte actual) throws IOException {
		if ( expected != actual) System.err.println( "check expected " + Integer.toHexString( 0xff & expected) + ", found " + Integer.toHexString( 0xff & actual));
	}

	static void pad( ByteBuffer buffer, int boundary) {
		buffer.position(( buffer.position() + boundary) & ~boundary);
	}

	static void dump( byte[] data) {
		dump( data, System.out);
	}

	static void dump( byte[] data, Appendable out) {
		ByteBuffer buf = ByteBuffer.wrap( data);
		Formatter fmt = new Formatter( out);

		int pos = buf.position();
		fmt.format( "%8x:", pos & ~0xf);
		StringBuilder builder = new StringBuilder();
		for ( int i = 0; i < ( pos & 0xf); i++) {
			fmt.format( "   ");
			builder.append( ' ');
		}
		while ( buf.hasRemaining()) {
			byte b = buf.get();
			fmt.format( " %2x", b);
			if ( ' ' <= b && b < 0x7f) {
				builder.append(( char) b);
			} else {
				builder.append( ' ');
			}
			if ( buf.hasRemaining() && ( buf.position() & 0xf) == 0) {
				fmt.format( " %s\n%8x:", builder, buf.position());
				builder.setLength( 0);
			}
		}
		buf.position( pos);
	}
}
