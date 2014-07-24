package be.ordina.utdemo.factoids.provider;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ppoissinger
 *
 */
public class FileFactProviderTest {
    /**
     * Test file loading
     * @throws IOException
     */
    @Test
    public final void testLoadStream() throws IOException {
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
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testLoadStreamIllegalArgument() throws IOException {
        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(null);
    }

    /**
     * Test file loading FIXME: Mock can help to trigger a IOStream error !
     * @throws IOException
     */
    @Ignore
    @Test(expected = IOException.class)
    public final void testLoadStreamIoException() throws IOException {
        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(null /* ? */);
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
