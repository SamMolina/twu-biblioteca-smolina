package com.twu.biblioteca.app.service;

import com.twu.biblioteca.app.model.Movie;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieService implements Asset{
    @Override
    public List getAssets(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileName, com.twu.biblioteca.app.util.Asset.MOVIE.toString());
        return movies;
    }

    @Override
    public List getAvailableAssets(List assets) {
        List<Movie> moviesAvailable = new ArrayList<>();

        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            if (movie.getCheckout() == false) {
                moviesAvailable.add(movie);
            }
        }

        return moviesAvailable;
    }

    @Override
    public void showAssets(List assets) {

    }

    @Override
    public boolean isAssetInAssets(List assets, Object assetToSearch) {
        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            if (movie.equals(assetToSearch)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object isAssetInAssets(List assets, String assetToSearch) {
        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            if (movie.getName().equals(assetToSearch)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List checkoutAsset(List assets, Object assetToCheckout) {
        assets.remove(assetToCheckout);
        return assets;
    }

    @Override
    public List returnAsset(List assets, Object assetToReturn) {
        assets.add(assetToReturn);
        return assets;
    }

    @Override
    public void updateCheckoutAsset(Object asset, boolean checkout, String message) {

    }
}
