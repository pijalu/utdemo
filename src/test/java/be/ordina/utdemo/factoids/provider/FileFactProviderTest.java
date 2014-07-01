package be.ordina.utdemo.factoids.provider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.junit.Test;

/**
 * @author ppoissinger
 * 
 */
public class FileFactProviderTest {
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
        FileFactProvider ffp = new FileFactProvider();
        InputStream io = mock(InputStream.class);
        when(io.available()).thenThrow(new IOException("Exception!"));
        ffp.loadStream(io);
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
