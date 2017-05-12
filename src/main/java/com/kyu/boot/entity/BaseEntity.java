package com.kyu.boot.entity;


import com.kyu.boot.converter.LocalDateAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

// 이 클래스를 직접 생성해서 사용할 일은 거의 없으므로 추상 클래스로 만드는 것을 권장
@MappedSuperclass
public abstract class BaseEntity {

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate changed;

    public LocalDate getChanged() {
        return changed;
    }

    public void setChanged(LocalDate changed) {
        this.changed = changed;
    }
}
