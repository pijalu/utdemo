package be.ordina.utdemo.factoids.provider;

import junit.framework.Assert;
import lombok.SneakyThrows;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.mock.Mock;
import org.unitils.mock.core.MockObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ppoissinger
 * 
 */
public class FileFactProviderTest extends UnitilsJUnit4 {

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
        ffp.loadStream((InputStream)null);
    }

    /**
     * Test file loading throwing IOException
     */
    @Test(expected = IOException.class)
    @SneakyThrows
    public final void testLoadStreamIoException() {
        Mock<InputStream> is = new MockObject<>(InputStream.class, this);
        is.raises(new IOException("Test!")).available();
        FileFactProvider ffp = new FileFactProvider();
        ffp.loadStream(is.getMock());
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
