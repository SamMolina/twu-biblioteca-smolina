package com.twu.biblioteca.app.impl;

public class MovieService {
    /*
    @Override
    public List getAssets(String fileName, String type) throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileName, type);
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
        System.out.print(BibliotecaConstants.SHOW_MOVIES.toString());
        System.out.print(new Movie().formatMovieInformation(BibliotecaConstants.NAME.name(), BibliotecaConstants.DIRECTOR.name(),
                BibliotecaConstants.YEAR.name(), BibliotecaConstants.RATING.name()));
        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            System.out.print(new Movie().formatMovieInformation(movie.getName(), movie.getDirector(), movie.getYear(), String.valueOf(movie.getRating())));
        }
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
        Movie movie = (Movie) assetToCheckout;
        if (new Movie().isAValidMovie(movie)) {
            if (movie.getCheckout() == false) {
                updateCheckoutAsset(movie, !movie.getCheckout(), BibliotecaConstants.ENJOY_THE_MOVIE.toString());
                assets.remove(movie);
            } else {
                updateCheckoutAsset(movie, movie.getCheckout(), BibliotecaConstants.BOOK_NO_AVAILABLE.toString());
            }
        }
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
    */
}
