package com.pvsrishabh.formfiller.domain.usecases.app_entry

import com.pvsrishabh.formfiller.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}