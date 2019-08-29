package com.github.calo001.fondo.base

import android.annotation.SuppressLint
import com.github.calo001.fondo.model.Photo
import com.github.calo001.fondo.repository.HistoryRepository
import com.github.calo001.fondo.repository.UnsplashRepository

abstract class BasePhotoInteractorImpl<P : BasePhotoPresenterContract>(open val presenter: P) :
    BasePhotoInteractorContract {
    override fun addToHistory(photo: Photo) {
        val repo = HistoryRepository()
        repo.saveToHistory(photo)
    }

    @SuppressLint("CheckResult")
    override fun getDownloadLink(id: String) {
        UnsplashRepository.getDownloadLinkLocation(id)
            .subscribe({ response ->
                presenter.onDownloadLinkSuccess(response.url)
            }, { error ->
                presenter.onError(error.localizedMessage)
            })
    }
}