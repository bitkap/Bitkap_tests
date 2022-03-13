package com.test.communs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U>
{
    @CreatedBy
    @Column(name = "created_by")
    @JsonProperty(value = "created_by", access = JsonProperty.Access.WRITE_ONLY)
    private U createdBy;

    @CreatedDate
    @Column(name = "created_date")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @JsonProperty(value = "created_date"/*, access = JsonProperty.Access.WRITE_ONLY*/)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    @JsonProperty(value = "updated_by", access = JsonProperty.Access.WRITE_ONLY)
    private U updatedBy;

    @LastModifiedDate
    @Column(name = "last_update")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @JsonProperty(value = "last_update", access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdate;
}