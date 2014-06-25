package be.ordina.utdemo.factoids.provider;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.easymock.EasyMockSupport;
import org.junit.Test;

/**
 * @author ppoissinger
 * 
 */
public class FileFactProviderTest extends EasyMockSupport {
    /**
     * Test file loading
     */
    @Test
    @SneakyThrows
    public final void testLoadStream() {
        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(this.getClass().getResourceAsStream("/testfacts.txt"));

        Assert.assertEquals(ffp.size(), 3);
        for (int i = 0; i < 3; ++i) {
            Assert.assertEquals(String.format("fact%d", i), ffp.getFact(i)
                    .getContent());
        }
    }

    /**
     * Test file loading without a valid stream
     */
    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public final void testLoadStreamIllegalArgument() {
        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(null);
    }

    /**
     * Test file loading
     */
    @Test(expected = IOException.class)
    @SneakyThrows
    public final void testLoadStreamIoException() {
        // No strict mock as underlying implementation may vary
        InputStream ioMock = createNiceMock(InputStream.class);
        expect(ioMock.available()).andThrow(new IOException("Available Oops"));
        replay(ioMock);

        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(ioMock);
    }

    /**
     * Check that empty facts are 0
     */
    @Test
    public final void testSizeEmptyFact() {
        FileFactProvider ffp = new FileFactProvider();
        Assert.assertEquals(0, ffp.size());
    }
}
