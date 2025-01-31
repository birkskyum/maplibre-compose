package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.viewinterop.UIKitInteropInteractionMode
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import co.touchlab.kermit.Logger
import cocoapods.MapLibre.MLNMapView
import dev.sargunv.maplibrecompose.core.IosMap
import dev.sargunv.maplibrecompose.core.MaplibreMap
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSURL

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUrl: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  IosMapView(
    modifier = modifier,
    styleUrl = styleUrl,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun IosMapView(
  modifier: Modifier,
  styleUrl: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  MeasuredBox(modifier = modifier.fillMaxSize()) { x, y, width, height ->
    val layoutDir = LocalLayoutDirection.current
    val insetPadding = WindowInsets.safeDrawing.asPaddingValues()

    val currentOnReset by rememberUpdatedState(onReset)
    var currentMap by remember { mutableStateOf<IosMap?>(null) }

    UIKitView(
      modifier = modifier.fillMaxSize(),
      properties =
        UIKitInteropProperties(interactionMode = UIKitInteropInteractionMode.NonCooperative),
      factory = {
        MLNMapView(
            frame =
              CGRectMake(
                x = x.value.toDouble(),
                y = y.value.toDouble(),
                width = width.value.toDouble(),
                height = height.value.toDouble(),
              ),
            styleURL = NSURL(string = styleUrl),
          )
          .also { mapView ->
            currentMap =
              IosMap(
                mapView = mapView,
                size = CGSizeMake(width.value.toDouble(), height.value.toDouble()),
                layoutDir = layoutDir,
                insetPadding = insetPadding,
                callbacks = callbacks,
                logger = logger,
              )
          }
      },
      update = { _ ->
        val map = currentMap ?: return@UIKitView
        map.size = CGSizeMake(width.value.toDouble(), height.value.toDouble())
        map.layoutDir = layoutDir
        map.insetPadding = insetPadding
        map.callbacks = callbacks
        map.styleUrl = styleUrl
        map.logger = logger
        update(map)
      },
      onReset = {
        currentOnReset()
        currentMap = null
      },
    )
  }
}
