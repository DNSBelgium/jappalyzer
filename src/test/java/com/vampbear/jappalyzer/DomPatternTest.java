package com.vampbear.jappalyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DomPatternTest {

    @Test
    public void shouldBeApplicableToSelectorWithAttributes() {
        Map<String, String> attributes = Collections.singletonMap("src", "www\\.resengo\\.\\w+");
        DomPattern pattern = new DomPattern("iframe[src*='resengo']", attributes);
        Document document = Jsoup.parse(
                "<html><body><iframe src='https://www.resengo.com/iframe'/></body></html>");
        assertThat(pattern.applicableToDocument(document)).isTrue();
    }

    @Test
    public void shouldNotMatchSelectorWithText() {
        DomPattern pattern = new DomPattern(
                "style, script",
                Collections.emptyMap(),
                Collections.emptyMap(),
                "(?:\\.[a-z]+|/media)(?:/[\\w-]+)?/(?:original_images/[\\w-]+|images/[\\w-.]+\\.(?:(?:fill|max|min)-\\d+x\\d+(?:-c\\d+)?|(?:width|height|scale)-\\d+|original))\\."
        );
        Document document = Jsoup.parse("<html><body><script>const index = 0;</script></body></html>");
        assertThat(pattern.applicableToDocument(document)).isFalse();
    }

    @Test
    public void shouldNotMatchOnEmptyAttribute() {
        Map<String, String> attributes = Collections.singletonMap("sveltekit:prefetch", "");
        DomPattern pattern = new DomPattern("a", attributes);
        Document document = Jsoup.parse("<html><body><a href=\"#\">link</a></body></html>");
        assertThat(pattern.applicableToDocument(document)).isFalse();
    }

}