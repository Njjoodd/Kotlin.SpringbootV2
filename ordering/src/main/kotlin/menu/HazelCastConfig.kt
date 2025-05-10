package com.coded.menu

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance

val hazelcastConfig = Config("hazelcast-menu-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig)
