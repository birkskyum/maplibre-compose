package dev.sargunv.maplibrecompose.demoapp.demos

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import dev.sargunv.maplibrecompose.compose.ClickResult
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.core.camera.CameraPosition
import dev.sargunv.maplibrecompose.demoapp.getAllStyleUrls
import io.github.dellisd.spatialk.geojson.Position

val NEW_YORK = Position(latitude = 40.744, longitude = -73.981)

@Composable
fun StyleSwitcherDemo() = Column {
  val styles = remember { getAllStyleUrls() }
  var selectedIndex by remember { mutableStateOf(0) }

  val cameraState = rememberCameraState(CameraPosition(target = NEW_YORK, zoom = 15.0, tilt = 30.0))

  MaplibreMap(
    modifier = Modifier.weight(1f),
    styleUrl = styles[selectedIndex].second,
    cameraState = cameraState,
    onMapClick = { pos, offset ->
      println("Clicked at $pos, $offset")
      val gotPos = cameraState.positionFromScreenLocation(offset)
      val gotOffset = cameraState.screenLocationFromPosition(pos)
      println("Calculated position $gotPos, $gotOffset")
      ClickResult.Pass
    }
  )

  SecondaryScrollableTabRow(selectedTabIndex = selectedIndex) {
    styles.forEachIndexed { index, pair ->
      Tab(
        selected = selectedIndex == index,
        onClick = { selectedIndex = index },
        text = { Text(text = pair.first, maxLines = 1, overflow = TextOverflow.Ellipsis) },
      )
    }
  }
}
