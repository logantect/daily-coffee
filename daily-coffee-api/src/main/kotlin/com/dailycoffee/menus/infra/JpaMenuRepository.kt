package com.dailycoffee.menus.infra

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaMenuRepository : MenuRepository, JpaRepository<Menu, UUID>
