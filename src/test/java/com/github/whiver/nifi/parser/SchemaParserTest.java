/*
 * MIT License
 *
 * NiFi Protobuf Processor
 * Copyright (c) 2017 William Hiver
 * https://github.com/whiver/nifi-protobuf-processor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.whiver.nifi.parser;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class SchemaParserTest {
    @Test
    public void compileProto() throws Exception {
        InputStream referenceSchema = SchemaParserTest.class.getResourceAsStream("/schemas/Person.desc");
        File tempSchemaRef;
        try (CompiledSchema resultSchema = SchemaParser.compileProto(SchemaParserTest.class.getResource("/schemas/Person.proto").getPath())) {
            Assert.assertTrue("Compiled Person.proto should be equal to Person.desc", IOUtils.contentEquals(referenceSchema, resultSchema.read()));
            tempSchemaRef = resultSchema.getSchemaFile();
        }
        Assert.assertFalse("Temporary .desc file should be removed from disk", tempSchemaRef.exists());
    }
}