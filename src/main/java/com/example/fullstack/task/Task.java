package com.example.fullstack.task;

import com.example.fullstack.project.Project; // Proje ilişkisi için gerekli olan sınıf
import com.example.fullstack.user.User;       // Kullanıcı ilişkisi için gerekli olan sınıf
import io.quarkus.hibernate.reactive.panache.PanacheEntity; // PanacheEntity, veritabanı işlemlerini kolaylaştırır
import org.hibernate.annotations.CreationTimestamp;        // Oluşturulma zamanını otomatik ekler

import javax.persistence.Column;       // Veritabanı sütun özelliklerini belirlemek için kullanılır
import javax.persistence.Entity;       // Bu sınıfın bir veritabanı varlığı olduğunu belirtir
import javax.persistence.ManyToOne;    // Varlıklar arasındaki ilişkiyi tanımlar
import javax.persistence.Table;        // Veritabanında hangi tabloya karşılık geleceğini belirtir
import javax.persistence.Version;      // Versiyon kontrolü sağlar (eşzamanlılık kontrolü için)
import java.time.ZonedDateTime;        // Saat dilimi bilgisi içeren tarih/zaman sınıfı

// Bu sınıf, "tasks" adlı bir veritabanı tablosunu temsil eder
@Entity
@Table(name = "tasks")
public class Task extends PanacheEntity {

    // Görev başlığını saklayan sütun
    // "nullable = false" ile boş bırakılamayacağını belirtir
    @Column(nullable = false)
    public String title;

    // Görev açıklamasını tutan sütun, en fazla 1000 karakter uzunluğunda olabilir
    @Column(length = 1000)
    public String description;

    // Görevin öncelik seviyesini (priority) temsil eder (örneğin, 1 = yüksek öncelik)
    public Integer priority;

    // Görevi gerçekleştirecek kullanıcıyı temsil eden ilişki
    // "optional = false" ile bir kullanıcı atanmasının zorunlu olduğunu belirtir
    @ManyToOne(optional = false)
    public User user;

    // Görevin tamamlanma zamanını saklar (tamamlanmışsa bir tarih atanır)
    public ZonedDateTime complete;

    // Görevin ait olduğu projeyi temsil eden ilişki (isteğe bağlıdır)
    @ManyToOne
    public Project project;

    // Görevin oluşturulma zamanını saklar
    // "@CreationTimestamp" ile otomatik olarak oluşturulma zamanı atanır
    @CreationTimestamp
    @Column(updatable = false, nullable = false) // Bu sütun güncellenemez ve boş bırakılamaz
    public ZonedDateTime created;

    // Veritabanı eşzamanlılık kontrolü için versiyon alanı
    // Hibernate, aynı anda birden fazla işlemin aynı kaydı değiştirmesini önlemek için bunu kullanır
    @Version
    public int version;
}
