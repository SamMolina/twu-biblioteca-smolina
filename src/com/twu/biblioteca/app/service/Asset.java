package com.twu.biblioteca.app.service;

import java.util.List;

public interface Asset {

    public List<Object> getAssets(String fileName);

    public List<Object> getAvailableAssets(List<Object> assets);

    public void showAssets(List<Object> assets);

    public boolean isAssetInAssets(List<Object> assets, Object assetToSearch);

    public Object isAssetInAssets(List<Object> assets, String assetToSearch);

    public Object checkoutAsset(Object object);

    public Object returnAsset(Object object);

    public void updateCheckoutBook(Object object, boolean checkout, String message);

    public List<Object> checkoutBook(List<Object> objects, Object object);

    public List<Object> returnBook(List<Object> objects, Object object);
}
