/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package oracle.stream.javaone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Dot {

	// static final String DOT_CMD = System.getProperty("DOT",
	// "/usr/local/bin/dot");

	public static void dotToSvg(InputStream dotIn, OutputStream svgOut) {
		try {
			String cmds[] = { "\"/Program Files (x86)/Graphviz2.38/bin/dot.exe\"", "-Tsvg" };
			Process p = Runtime.getRuntime().exec(cmds);
			// Send the dot file
			try (OutputStream pOut = p.getOutputStream()) {
				writeAll(dotIn, pOut);
			}

			// Read the svg file
			try (InputStream pIn = p.getInputStream()) {
				writeAll(pIn, svgOut);
			}

			int r = p.waitFor();
			if (r != 0)
				throw new RuntimeException("Conversion from dot to svg failed: " + r);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void writeAll(InputStream from, OutputStream to) throws IOException {
		byte[] buf = new byte[4096];

		int len;
		while ((len = from.read(buf)) != -1) {
			to.write(buf, 0, len);
		}
	}
}
