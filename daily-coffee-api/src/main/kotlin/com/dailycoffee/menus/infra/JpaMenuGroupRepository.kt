package com.dailycoffee.menus.infra

import com.dailycoffee.menus.domain.MenuGroup
import com.dailycoffee.menus.domain.MenuGroupRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaMenuGroupRepository : MenuGroupRepository, JpaRepository<MenuGroup, UUID>
