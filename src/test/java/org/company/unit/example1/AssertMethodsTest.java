package org.company.unit.example1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🎯 Temel Karşılaştırmalar
 * assertEquals(expected, actual)
 * <p>
 * assertNotEquals(unexpected, actual)
 * <p>
 * assertArrayEquals(expectedArray, actualArray)
 * <p>
 * assertIterableEquals(expected, actual)
 * <p>
 * assertLinesMatch(expectedLines, actualLines) (özellikle çok satırlı metinler için)
 * <p>
 * 🎯 Doğruluk Kontrolleri
 * assertTrue(condition)
 * <p>
 * assertFalse(condition)
 * <p>
 * 🎯 Null Kontrolleri
 * assertNull(object)
 * <p>
 * assertNotNull(object)
 * <p>
 * 🎯 Nesne Karşılaştırmaları
 * assertSame(expected, actual)
 * <p>
 * assertNotSame(unexpected, actual)
 * <p>
 * 🎯 İstisna (Exception) Kontrolleri
 * assertThrows(expectedExceptionClass, executable)
 * <p>
 * assertDoesNotThrow(executable)
 * <p>
 * 🎯 Gruplu Assertion
 * assertAll(executables...)
 * (Birden fazla assertion'ı tek bir blokta toplar ve hepsini çalıştırır.)
 * <p>
 * 🎯 Manuel Başarısızlık
 * fail()
 * (Testi elle başarısız yapmak için.)
 * <p>
 * 🎯 Zaman Aşımı Kontrolleri
 * assertTimeout(duration, executable)
 * <p>
 * assertTimeoutPreemptively(duration, executable)
 */

class AssertMethodsTest {

    AssertMethods assertMethods = new AssertMethods();

    @Test
    void notEquals() {
        assertNotEquals(4, assertMethods.square(2),
                "The square of 2 should not be 4, it should be 4");
    }

    @Test
    void arrayEquals() {
        int[] expected = {1, 4, 9};
        int[] inputArray = {1, 2, 3};
        int[] actual = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            actual[i] = assertMethods.square(inputArray[i]);
        }
        // Assert that the two arrays are equal
        assertArrayEquals(expected, actual,
                "The squared values of the input array should match the expected array");
    }

    @Test
    void IterableEquals() {
        Iterable<Double> expected = List.of(1D, 4D, 9D);
        Iterable<Double> actual = List.of((double) assertMethods.square(1), (double) assertMethods.square(2), (double) assertMethods.square(3));

        // Assert that the two iterables are equal
        assertIterableEquals(expected, actual, "The squared values of the input iterable shoulde match the expected iterable");
    }

    @Test
    void linesMatch() {
        List<String> expected = List.of("hello", "world");
        List<String> actual = List.of("hello", "world");
        assertLinesMatch(expected, actual, "The lines should match exactly");
    }

    @Test
    void isEven() {
        assertTrue(assertMethods.isEven(4), "4 should be even");
        assertFalse(assertMethods.isEven(5), "5 should not be even");
        assertTrue(assertMethods.isEven(0), "0 should be considered even");
        assertFalse(assertMethods.isEven(-3), "-3 should not be even");
    }

    @Test
    void isOdd() {
        assertTrue(assertMethods.isOdd(3), "3 should be odd");
    }

    @Test
    void isNull() {
        Integer nullValue = null;
        assertNull(nullValue, "The value should be null");
    }

    @Test
    void isNotNull() {
        Integer notNullValue = 5;
        assertNotNull(notNullValue, "The value should not be null");
    }

    @Test
    void isSame() {
        Integer a = 5;
        Integer b = 5;
        assertSame(a, b); // Autoboxing nedeniyle aynı referanstır
        Integer c = new Integer(5);
        assertNotSame(a, c); // Farklı referanslar
    }

    @Test
    void myThrows() {
        // assertThrows ile beklenen istisnanın fırlatılmasını kontrol et
        assertThrows(NullPointerException.class, () -> {
            String str = null;
            str.length(); // Bu işlem bir NullPointerException fırlatır
        }, "NullPointerException should be thrown when accessing length of null string");
    }

    @Test
    void myDoesNotThrow() {
        // assertDoesNotThrow ile beklenen istisnanın fırlatılmamasını kontrol et
        assertDoesNotThrow(() -> {
            String str = "Hello, World!";
            int length = str.length(); // Bu işlem bir istisna fırlatmaz
        }, "No exception should be thrown when accessing length of a non-null string");
    }

    @Test
    void groupAssert(){
        assertAll("Group of assertions",
                () -> assertEquals(4, assertMethods.square(2), "Square of 2 should be 4"),
                () -> assertTrue(assertMethods.isEven(4), "4 should be even"),
                () -> assertFalse(assertMethods.isOdd(4), "4 should not be odd")
        );
    }

    @Test
    void failTest() {
        // fail() metodu ile testin başarısız olduğunu belirt
        fail("This test is intentionally failed to demonstrate the fail method");
    }

    @Test
    void timeoutTest() {
        // assertTimeout ile belirli bir süre içinde işlemin tamamlanmasını kontrol et
        assertTimeout(java.time.Duration.ofMillis(100), () -> {
            Thread.sleep(50); // 50 ms bekle
        }, "The operation should complete within 100 milliseconds");
    }

    @Test
    void timeoutPreemptivelyTest() {
        // assertTimeoutPreemptively ile işlemin belirli bir süre içinde tamamlanmasını kontrol et
        assertTimeoutPreemptively(java.time.Duration.ofMillis(100), () -> {
            Thread.sleep(50); // 50 ms bekle
        }, "The operation should complete within 100 milliseconds preemptively");
    }

    //🧠 Teknik Fark (Kısaca)
    //assertTimeout: test kodunu çağıran thread içinde çalıştırır.
    //
    //assertTimeoutPreemptively: test kodunu farklı thread içinde çalıştırır, timeout durumunda bu thread'i durdurur.

    /*
╔══════════════════════════════════════╦═══════════════════════════════════════════════════════╦════════════════════════════════════════════════════════╗
║ Özellik                             ║ assertTimeout                                          ║ assertTimeoutPreemptively                             ║
╠══════════════════════════════════════╬═══════════════════════════════════════════════════════╬════════════════════════════════════════════════════════╣
║ Çalışma mantığı                     ║ Kod tamamlanana kadar çalışır, sonunda süre kontrol edilir ║ Kod zaman aşımına ulaşırsa çalışmayı durdurur          ║
║ Thread iptali                       ║ ❌ Thread durdurulmaz                                   ║ ✅ Süre geçerse test thread'i durdurulur               ║
║ Güvenli mi?                         ║ ✅ Daha güvenli (kaynaklara zarar vermez)               ║ ⚠️ Riskli olabilir (kaynaklar düzgün kapanmayabilir)   ║
║ Sonsuz döngü/kilitlenme testi      ║ ❌ Algılamaz, test asılı kalabilir                     ║ ✅ Süre dolunca durdurur                               ║
║ Performans testi                    ║ ✅ Uygun                                                ║ ✅ Uygun ancak agresif                                 ║
║ Hangi thread'de çalışır?           ║ Ana test thread'i                                       ║ Ayrı thread (forked)                                   ║
╚══════════════════════════════════════╩═══════════════════════════════════════════════════════╩════════════════════════════════════════════════════════╝
*/


}