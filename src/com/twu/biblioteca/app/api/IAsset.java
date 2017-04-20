package com.twu.biblioteca.app.api;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface IAsset<T> {

    List<T> getAssets(String fileName, String type) throws ParserConfigurationException, SAXException, IOException;

    List<T> getAvailableAssets(List<T> assets);

    void showAssets(List<T> assets, String typeAsset);

    boolean isAssetInAssets(List<T> assets, Object assetToSearch);

    T isAssetInAssets(List<T> assets, String assetToSearch);

    List<T> checkoutAsset(List<T> assets, T asset);

    List<T> returnAsset(List<T> assets, T asset);

    void updateCheckoutAsset(T asset, boolean checkout, String message);
}
