package com.coded.menu

import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<MenuItemEntity, Long>
