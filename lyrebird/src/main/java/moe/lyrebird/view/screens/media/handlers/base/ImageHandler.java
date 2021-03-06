/*
 *     Lyrebird, a free open-source cross-platform twitter client.
 *     Copyright (C) 2017-2018, Tristan Deloche
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package moe.lyrebird.view.screens.media.handlers.base;

import moe.lyrebird.model.io.AsyncIO;
import moe.lyrebird.view.assets.ImageResources;
import moe.lyrebird.view.screens.media.display.MediaDisplaySceen;
import moe.lyrebird.view.screens.media.handlers.EmbeddedMediaViewHelper;
import moe.lyrebird.view.screens.media.handlers.MediaHandler;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static moe.lyrebird.view.screens.media.MediaEmbeddingService.EMBEDDED_MEDIA_RECTANGLE_SIDE;

/**
 * Basic implementation for image handlers that can extract a URL to preview.
 *
 * @param <T> The input type for the original image description type
 *
 * @see EmbeddedMediaViewHelper
 */
public abstract class ImageHandler<T> implements MediaHandler<T> {

    private final AsyncIO asyncIO;
    private final EmbeddedMediaViewHelper embeddedMediaViewHelper;

    public ImageHandler(
            final AsyncIO asyncIO,
            final EmbeddedMediaViewHelper embeddedMediaViewHelper
    ) {
        this.asyncIO = asyncIO;
        this.embeddedMediaViewHelper = embeddedMediaViewHelper;
    }

    /**
     * Implementation side of the miniature creation once the image has been mapped to an URL.
     *
     * @param imageUrl The image described as an URL
     *
     * @return An {@link ImageView} with asynchronously loaded image miniature as display image with by default (until
     * the asynchronous call is done) a static image ({@link ImageResources#GENERAL_LOADING_REMOTE}). This {@link ImageView}
     * will open a {@link MediaDisplaySceen#PHOTO} screen displaying the media when it is clicked.
     */
    protected Pane handleMediaSource(final String imageUrl) {
        return embeddedMediaViewHelper.makeWrapperWithIcon(
                MediaDisplaySceen.PHOTO,
                ImageResources.GENERAL_LOADING_REMOTE,
                imageUrl,
                imageView -> loadMiniatureAsync(imageView, imageUrl)
        );
    }

    /**
     * Helper for loading the asynchronous image inside the preview {@link ImageView}.
     *
     * @param imageView      The preview {@link ImageView}.
     * @param actualImageUrl The media's URL compliant description
     */
    private void loadMiniatureAsync(final ImageView imageView, final String actualImageUrl) {
        asyncIO.loadImageMiniature(actualImageUrl, EMBEDDED_MEDIA_RECTANGLE_SIDE, EMBEDDED_MEDIA_RECTANGLE_SIDE)
               .thenAcceptAsync(imageView::setImage, Platform::runLater);
    }

}
