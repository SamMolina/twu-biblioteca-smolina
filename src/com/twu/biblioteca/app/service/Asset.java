package com.twu.biblioteca.app.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.*;

public interface Asset<T> {

    public List<T> getAssets(String fileName) throws ParserConfigurationException, SAXException, IOException;

    public List<T> getAvailableAssets(List<T> assets);

    public void showAssets(List<T> assets);

    public boolean isAssetInAssets(List<T> assets, Object assetToSearch);

    public T isAssetInAssets(List<T> assets, String assetToSearch);

    public T checkoutAsset(T asset);

    public T returnAsset(T asset);

    public void updateCheckoutAsset(T asset, boolean checkout, String message);

    public List<T> checkoutAsset(List<T> assets, T assetToCheckout);

    public List<T> returnAsset(List<T> assets, T assetToReturn);
}
