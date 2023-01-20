package com.paychex.moviemetadataservicepyx.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordUtilsTest {

    @Test
    public void testCapitalizeWords_success() {
        String title = "how to shut up a quarrelsome wife";
        String expected = "How To Shut Up A Quarrelsome Wife";
        String result = WordUtils.capitalizeTitle(title);
        assertEquals(expected, result);
    }
}