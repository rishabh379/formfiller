package com.pvsrishabh.formfiller.domain.usecases.app_entry

import com.pvsrishabh.formfiller.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}