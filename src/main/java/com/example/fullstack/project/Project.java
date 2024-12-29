package com.example.fullstack.project;

import com.example.fullstack.user.User; // Kullanıcı ilişkisi için gerekli olan sınıf
import io.quarkus.hibernate.reactive.panache.PanacheEntity; // PanacheEntity, Hibernate Reactive işlemlerini kolaylaştırır
import org.hibernate.annotations.CreationTimestamp;        // Nesnenin oluşturulma zamanını otomatik ekler

import javax.persistence.Column;       // Veritabanı sütun özelliklerini belirlemek için kullanılır
import javax.persistence.Entity;       // Bu sınıfın bir JPA varlığı olduğunu belirtir
import javax.persistence.ManyToOne;    // Varlıklar arasındaki ilişkiyi tanımlar
import javax.persistence.Table;        // Veritabanında hangi tabloya karşılık geleceğini belirtir
import javax.persistence.UniqueConstraint; // Tablodaki sütunların bir kombinasyonu için benzersizlik kısıtı tanımlar
import javax.persistence.Version;      // Versiyon kontrolü sağlar (eşzamanlılık kontrolü için)
import java.time.ZonedDateTime;        // Saat dilimi bilgisi içeren tarih/zaman sınıfı

// Bu sınıf, "projects" adlı bir veritabanı tablosunu temsil eder
@Entity
@Table(
        name = "projects", // Veritabanındaki tablonun adı
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "user_id"}) // "name" ve "user_id" sütunlarının kombinasyonu benzersiz olmalı
        }
)
public class Project extends PanacheEntity {

    // Proje adını saklayan sütun
    // "nullable = false" ile bu alanın boş bırakılamayacağını belirtir
    @Column(nullable = false)
    public String name;

    // Proje ile ilişkilendirilmiş kullanıcıyı temsil eder
    // "optional = false" ile kullanıcı atanmasının zorunlu olduğunu belirtir
    @ManyToOne(optional = false)
    public User user;

    // Projenin oluşturulma zamanını saklar
    // "@CreationTimestamp" ile otomatik olarak oluşturulma zamanı atanır
    @CreationTimestamp
    @Column(updatable = false, nullable = false) // Bu sütun güncellenemez ve boş bırakılamaz
    public ZonedDateTime created;

    // Veritabanı eşzamanlılık kontrolü için versiyon alanı
    // Hibernate, aynı anda birden fazla işlemin aynı kaydı değiştirmesini önlemek için bunu kullanır
    @Version
    public int version;
}
