import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class MainTest {
    @Test
    public void initialTest() {
        assertThat(new Main().initial(), is(true));
    }
}
