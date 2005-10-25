package org.freecompany.redline.header;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Signature extends AbstractHeader {

	public Signature() {
		for ( SignatureTag tag : SignatureTag.values()) tags.put( tag.getCode(), tag);
	}

	public enum SignatureTag implements Tag {
		SIGNATURES( 62, 7, "signatures"),
		SIGSIZE( 257, 4, "sigsize"),
		LEGACY_SIGSIZE( 1000, 4, "sigsize"),
		PGP( 259, 7, "pgp"),
		LEGACY_PGP( 1002, 7, "pgp"),
		MD5( 261, 7, "md5"),
		LEGACY_MD5( 1004, 7, "md5"),
		GPG( 262, 7, "gpg"),
		LEGACY_GPG( 1005, 7, "gpg"),
		PAYLOADSIZE( 1007, 4, "payloadsize"),
		SHA1HEADER( 269, 6, "sha1header"),
		LEGACY_SHA1HEADER( 1010, 6, "sha1header"),
		DSAHEADER( 267, 7, "dsaheader"),
		LEGACY_DSAHEADER( 1011, 7, "dsaheader"),
		RSAHEADER( 268, 7, "rsaheader"),
		LEGACY_RSAHEADER( 1012, 7, "rsaheader");

		private int code;
		private int type;
		private String name;

		private SignatureTag( final int code, final int type, final String name) {
			this.code = code;
			this.type = type;
			this.name = name;
		}

		public int getCode() { return code; }
		public int getType() { return type; }
		public String getName() { return name; }
	}
}
