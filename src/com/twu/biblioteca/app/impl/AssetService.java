package com.twu.biblioteca.app.impl;

import com.twu.biblioteca.app.api.IAsset;
import com.twu.biblioteca.app.model.Asset;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.util.BibliotecaConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssetService implements IAsset {

    @Override
    public List<Object> getAssets(String fileName, String type) throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new XMLFileParser().parserFile(fileName, type);
        return assets;
    }

    @Override
    public List<Object> getAvailableAssets(List assets) {
        List<Object> availableAssets = new ArrayList<>();
        for (Object object: assets) {
            Asset asset = (Asset) object;
            if (asset.getCheckout() == false)
                availableAssets.add(asset);
        }
        return availableAssets;
    }

    @Override
    public void showAssets(List assets, String typeAsset) {
        if (typeAsset.equals(BibliotecaConstants.BOOK.toString())) {
            System.out.print(new Book().showAsset());
            System.out.print(new Asset().formatAssetInformation(BibliotecaConstants.TITLE.name(), BibliotecaConstants.AUTHOR.name(),
                    BibliotecaConstants.YEAR.name()));

            for (Object object: assets) {
                Asset asset = (Asset) object;
                System.out.print(new Asset().formatAssetInformation(asset.getName(), asset.getAuthor(), asset.getYear()));
            }
        } else if (typeAsset.equals(BibliotecaConstants.MOVIE.toString())) {
            System.out.print(new Movie().showAsset());
            System.out.print(new Movie().formatAssetInformation(BibliotecaConstants.NAME.name(), BibliotecaConstants.DIRECTOR.name(),
                    BibliotecaConstants.YEAR.name(), BibliotecaConstants.RATING.name()));

            for (Object object : assets) {
                Movie movie = (Movie) object;
                System.out.print(new Movie().formatAssetInformation(movie.getName(), movie.getAuthor(), movie.getYear(), String.valueOf(movie.getRating())));
            }
        }
    }

    @Override
    public boolean isAssetInAssets(List assets, Object assetToSearch) {
        for (Object object: assets) {
            Asset asset = (Asset) object;
            if (asset.equals((Asset) assetToSearch)) return true;
        }
        return false;
    }

    @Override
    public Object isAssetInAssets(List assets, String assetToSearch) {
        for (Object object: assets) {
            Asset asset = (Asset) object;
            if (asset.getName().equals(assetToSearch)) return asset;
        }
        return null;
    }

    @Override
    public List checkoutAsset(List assets, Object assetToCheckout) {
        Asset asset = (Asset) assetToCheckout;
        if (new Asset().isAValidAsset(asset) == true) {
            if (asset.getCheckout() == false) {
                updateCheckoutAsset(asset, !asset.getCheckout(), asset.successCheckout());
                assets.remove(asset);
            } else {
                updateCheckoutAsset(asset, asset.getCheckout(), asset.noAvailableAsset());
            }
        }
        return assets;
    }

    @Override
    public List<Object> returnAsset(List assets, Object assetToReturn) {
        Asset asset = (Asset) assetToReturn;
        if (new Asset().isAValidAsset(asset) == true) {
            if (asset.getCheckout() == true) {
                updateCheckoutAsset(asset, !asset.getCheckout(), asset.successReturn());
                assets.add(asset);
            } else {
                updateCheckoutAsset(asset, asset.getCheckout(), asset.unsuccessfulReturn());
            }
        }
        return assets;
    }

    @Override
    public void updateCheckoutAsset(Object assetToUpdate, boolean checkout, String message) {
        Asset asset = (Asset) assetToUpdate;
        asset.setCheckout(checkout);
        System.out.print(message);
    }
}
