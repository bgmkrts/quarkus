package com.example.fullstack.user;

import io.quarkus.hibernate.reactive.panache.PanacheEntity; // PanacheEntity, Hibernate Reactive işlemleri için kullanılır
import org.hibernate.annotations.CreationTimestamp;        // Nesnenin oluşturulma zamanını otomatik olarak ekler

import javax.persistence.CollectionTable; // ElementCollection için tablo oluşturmayı sağlar
import javax.persistence.Column;          // Veritabanı sütun özelliklerini belirler
import javax.persistence.ElementCollection; // Bir koleksiyonun (örneğin liste) bir varlıkla ilişkili olarak saklanmasını sağlar
import javax.persistence.Entity;          // Bu sınıfın bir JPA varlığı olduğunu belirtir
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;      // İlişkiyi temsil eden yabancı anahtar sütununu belirtir
import javax.persistence.Table;           // Veritabanında hangi tabloya karşılık geldiğini belirtir
import javax.persistence.Version;         // Versiyon kontrolü için kullanılır (eşzamanlılık kontrolü)
import java.time.ZonedDateTime;           // Saat dilimi bilgisi içeren tarih/zaman sınıfı
import java.util.List;                    // Rolleri saklamak için kullanılan liste yapısı

// Bu sınıf, "users" adlı bir veritabanı tablosunu temsil eder
@Entity
@Table(name = "users") // Bu sınıfın veritabanında "users" tablosuyla eşleştirileceğini belirtir
public class User extends PanacheEntity {

    // Kullanıcının adını saklayan sütun
    // "unique = true" ile bu sütun için benzersizlik kısıtı eklenmiştir
    // "nullable = false" ile bu alanın boş olamayacağı belirtilmiştir
    @Column(unique = true, nullable = false)
    public String name;

    // Kullanıcının şifresini saklayan sütun
    // "nullable = false" ile bu alanın boş bırakılamayacağı belirtilmiştir
    @Column(nullable = false)
    String password;

    // Kullanıcının oluşturulma zamanını saklayan sütun
    // "@CreationTimestamp" ile bu alan otomatik olarak atanır
    @CreationTimestamp
    @Column(updatable = false, nullable = false) // Bu sütun güncellenemez ve boş olamaz
    public ZonedDateTime created;

    // Versiyon kontrolü için kullanılan alan
    // Hibernate, eşzamanlılık kontrolü için bunu kullanır
    @Version
    public int version;

    // Kullanıcının rollerini saklayan alan
    // "ElementCollection" ile bu birden fazla değeri saklayabilir (örneğin, kullanıcı rolleri)
    @ElementCollection(fetch = FetchType.EAGER) // Roller her zaman yüklenir
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    // Roller, "user_roles" adlı ayrı bir tabloya saklanır. Bu tablonun yabancı anahtar sütunu "id"dir
    @Column(name = "role") // "user_roles" tablosundaki sütunun adı "role" olacaktır
    public List<String> roles; // Rolleri temsil eden liste
}
