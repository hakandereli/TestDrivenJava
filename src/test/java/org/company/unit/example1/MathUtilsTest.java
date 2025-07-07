package org.company.unit.example1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * Testin amacı hataların önüne geçmek ve kodun beklenen şekilde çalıştığını doğrulamaktır.
 * Manuel testler yapmak yerine otomatik testler yazmak, kodun güvenilirliğini artırır.
 * Testler, kod değişiklikleri yapıldığında regresyon hatalarını önlemek için de kullanılır.
 * Testler, kodun belirli bir işlevselliği doğru bir şekilde yerine getirip getirmediğini kontrol eder.
 * Test klasörleri src main java şeklinde ilerleyen dizin ile aynı tree yapısında olmalı mevcut class isminin sonuna camel case Test eklenmeli
 * Test classları public ve void olmalı bunun dışında kabul edilmez.
 * @Test anatasyonu ile Junit i kullanarak yazabilirsin.
 * Projeye maven projesi ise aşağıdaki plugin yer almalı ki mvn test komutu ile testler çalışsın.
 *   <build>
 *     <plugins>
 *       <!-- ✅ Surefire Plugin: testleri çalıştırmak için -->
 *       <plugin>
 *         <groupId>org.apache.maven.plugins</groupId>
 *         <artifactId>maven-surefire-plugin</artifactId>
 *         <version>3.1.2</version>
 *       </plugin>
 *     </plugins>
 *     </build>
 *     clean install işleminde menüden testleri kapatırsan testler ignore edilir.
 *     Test methodlarının isimleri mantıklı ve açıklayıcı olmalı.
 *     assertEquals metodu ile beklenen ve elde edilen değerleri karşılaştırabilirsin.
 *     assertEquals(expected, actual, message) şeklinde kullanılır.
 *     expected beklenen değer, actual elde edilen değer ve message ise hata durumunda gösterilecek mesajdır.
 *     testler sıra ile çalışır ve her test metodu bağımsız olarak çalışır.
 *     testleri devre dışı bırakmak için @Disabled anotasyonunu kullanabilirsin. içerisine nedenini yazabilirsin.
 *
 */

class MathUtilsTest {

    MathUtils mathUtils = new MathUtils();

    // Test cases for the square method in MathUtils class
    @Test
    void square() {
        int result = mathUtils.square(5);
        assertEquals(25, result, "The square of 5 should be 25");
    }

    @Test
    @Disabled("Reason")
        // This test is disabled and will not run
    void squareNegative() {
        int result = mathUtils.square(-3);
        assertEquals(9, result, "The square of -3 should be 9");
    }

    @Test
    void squareZero() {
        int result = mathUtils.square(0);
        assertEquals(0, result, "The square of 0 should be 0");
    }
}