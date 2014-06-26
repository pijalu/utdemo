package be.ordina.utdemo.factoids.provider;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import lombok.SneakyThrows;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * @author ppoissinger
 * 
 */
@RunWith(JMockit.class)
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

    @Mocked
    InputStream exceptionIs;

    /**
     * Test file loading
     */
    @Test(expected = IOException.class)
    @SneakyThrows
    public final void testLoadStreamIoException() {
        FileFactProvider ffp = new FileFactProvider();

        new NonStrictExpectations() {
            {
                exceptionIs.available();
                result = new IOException("OOps");
            }
        };

        ffp.loadStream(exceptionIs);
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
