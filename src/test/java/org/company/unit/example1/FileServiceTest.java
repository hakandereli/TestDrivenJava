package org.company.unit.example1;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Java'da JUnit testlerinde @BeforeEach ve @AfterEach (eski JUnit 4'te @Before ve @After)
 * anotasyonları, her test metodundan önce ve sonra çalışan metotları tanımlamak için kullanılır.
 * Bu sayede testler için gerekli olan ortak hazırlık işlemleri veya temizlik işlemleri tek bir yerde tanımlanabilir.
 * Özellikle her test çalıştığında yeniden oluşturulması gereken nesneler varsa
 * veya testin oluşturduğu kaynakları (örneğin dosya, bağlantı, veritabanı kaydı) temizlemeniz gerekiyorsa,
 * bu anotasyonlar kaçınılmaz hale gelir.
 */

/**
 * Bir servisin, diske geçici bir dosya yazdığını ve sonra bu dosyayı okuduğunu test edelim.
 * Eğer @BeforeEach ve @AfterEach kullanmazsanız,
 * testler arasında dosya kalıntıları oluşur ve diğer testler etkilenir.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceTest {

    private File tempFile;
    private FileService fileService;

    @BeforeAll
    void beforeAllTests() { //@TestInstance(TestInstance.Lifecycle.PER_CLASS) olmasa static olması gerekirdi
        System.out.println("==> Tüm testler başlamadan önce çalışır");
    }

    @AfterAll
    void afterAllTests() { //@TestInstance(TestInstance.Lifecycle.PER_CLASS) olmasa static olması gerekirdi
        System.out.println("==> Tüm testler bittikten sonra çalışır");
    }

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("testfile", ".txt");
        fileService = new FileService(tempFile);
        System.out.println("==> Her testten önce çalışır: Geçici dosya oluşturuldu: " + tempFile.getAbsolutePath());
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        System.out.println("==> Her testten sonra çalışır: Geçici dosya silindi.");
    }

    @Test
    void testWriteAndRead() throws IOException {
        fileService.writeToFile("Hello World");
        String result = fileService.readFromFile();
        assertEquals("Hello World", result);
    }

    @Test
    void testOverwrite() throws IOException {
        fileService.writeToFile("First");
        fileService.writeToFile("Second");
        String result = fileService.readFromFile();
        assertEquals("Second", result);
    }

    /**
     * Neden @BeforeEach ve @AfterEach Şart?
     * Geçici dosya her testten önce oluşturuluyor, böylece testler birbirinden bağımsız hale geliyor.
     *
     * Dosya her testten sonra siliniyor, yoksa diskte birikerek sonraki testleri bozar veya test başarısız olur.
     *
     * Eğer bu setUp ve tearDown işlemleri her testin içinde ayrı ayrı yazılsaydı, kod tekrarı olurdu ve bakımı zorlaşırdı.
     */

    /**
     * 📌 Sonuç
     * @BeforeEach ve @AfterEach özellikle:
     *
     * Geçici kaynaklar (dosya, DB bağlantısı)
     *
     * Mock nesne ayarları
     *
     * Ortak test verisi oluşturma
     *
     * Temizlik işlemleri
     *
     * için kaçınılmaz hale gelir. Bunlar olmadan doğru izole testler yazmak çoğu durumda mümkün değildir.
     */

    /** diğerleri için örnek yazılacak
     * ✅ Tüm Before/After Anotasyonları (JUnit 5)
     * Anotasyon	Ne zaman çalışır?	Açıklama
     * @BeforeEach Her test metodu öncesi	Her testten önce çağrılır. Ortak hazırlık işlemleri için.
     * @AfterEach Her test metodu sonrası	Her testten sonra çağrılır. Temizlik işlemleri için.
     * @BeforeAll Tüm testlerden önce bir kez	Sınıf içindeki testler çalışmadan önce sadece 1 kere çalışır. Genelde static metot olmalıdır.
     * @AfterAll Tüm testlerden sonra bir kez	Test sınıfındaki tüm testler bittikten sonra 1 kere çalışır. Genelde static metot olmalıdır.
     * @TestInstance(Lifecycle.PER_CLASS) Tüm testler için tek bir test sınıfı örneği kullanmak için	Böylece @BeforeAll ve @AfterAll metotları static olmak zorunda kalmaz.
     * @TestInstance(TestInstance.Lifecycle) anotasyonu, JUnit 5'te test sınıfı örneklerinin (instance) yaşam döngüsünü kontrol etmek için kullanılır.
     * Bu anotasyon sayesinde JUnit’in test sınıfınız için her test metodu öncesi yeni bir nesne mi oluşturacağını yoksa tüm testlerde aynı nesneyi mi kullanacağını belirleyebilirsiniz.
     *
     * 🔄 TestInstance.Lifecycle Parametreleri
     * java
     * Kopyala
     * Düzenle
     * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
     * Lifecycle enum'unda 2 değer vardır:
     *
     * ✅ 1. PER_METHOD (Varsayılan)
     * Açıklama: Her test metodu için yeni bir test sınıfı nesnesi (instance) oluşturur.
     *
     * Avantajı: Testler birbirinden tamamen izole olur.
     *
     * Dezavantajı: @BeforeAll ve @AfterAll metotları static olmak zorundadır.
     *
     * ✅ 2. PER_CLASS
     * Açıklama: Test sınıfı bir kez örneklenir, tüm testler aynı nesne üzerinde çalışır.
     *
     * Avantajı: @BeforeAll ve @AfterAll gibi metotlar static olmak zorunda değildir.
     *
     * Ekstra: Testler arasında örneğin iç durumu (field’lar) korunur.
     *
     * PerClassExample ve PerMethodExample örneklerine bakabilirsiniz.
     *
     * @BeforeTestExecution Her test metodunun başlamasından hemen önce	Daha özel zamanlama için kullanılabilir. (az kullanılır)
     * @AfterTestExecution Her test metodunun bitiminden hemen sonra	Genelde zaman ölçümü/loglama için tercih edilir.
     */
}