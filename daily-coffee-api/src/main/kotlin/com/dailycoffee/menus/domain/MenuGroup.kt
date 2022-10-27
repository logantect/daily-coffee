package com.dailycoffee.menus.domain

import com.dailycoffee.support.utils.IdGenerator
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "menu_group")
class MenuGroup(
    @Id @Column(columnDefinition = "BINARY(16)") val id: UUID,
    name: MenuGroupName,
) {
    @Embedded
    var name: MenuGroupName = name
        protected set

    constructor(name: String) : this(IdGenerator.createId(), MenuGroupName(name))
}
