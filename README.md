# Jetpack Compose Adaptive Screen Size Support Example

This is a simple example Android projects that shows how one potentially can support devices with
adaptive screen sizes, also known as foldable devices, with Jetpack Compose.

### Screenshots from a Horizontal Fold Emulator

<table style="max-width: 500px;">
<tr>
<td>
<div style="display: flex; flex-direction: column; align-items: center">
<img src="./images/fold_collapsed_screenshot.png" alt="Screenshot of folded setting">
<p>Folded</p>
</div>
</td>
<td>
<div style="display: flex; flex-direction: column; align-items: center">
<img src="./images/fold_expanded_screenshot.png" alt="Screenshot of expanded setting">
<p>Expanded</p>
</div>
</td>
</tr>
</table>

## What this app is about

It's a simple note app that solely allows you to look into notes as well as create new ones. It's
not meant to be a full-fledged note app, but rather a simple example of how one can support foldable
devices with Jetpack Compose.

In the *folded* state of a foldable device, it uses a regular layout with navigation. However, in
the *expanded* state, it shows the list of notes and the details on one screen.

### Adapting the layout to the screen size

The example heavily makes use of
the [Accompanist Adaptive](https://google.github.io/accompanist/adaptive/) package.
With the help of this package we can evaluate on runtime the current device and its screen state.

If the current screen size is eligible, we not only show a NavigationRail instead of a bottom bar, but we also show the
main
content of the app with the help of the [`TwoPane`](https://google.github.io/accompanist/adaptive/#twopane) composable.

If it's not, which is the case if the device's screen is just statically too small or it's really a foldable and
currently collapsed, we stick to a regular single pane approach with a ordinary navigation strategy.

The interesting parts can found in the `NotesTab.kt` file as well as in `NotesApp.kt`.

### Libraries used

* [Compose Destinations](https://github.com/raamcosta/compose-destinations) for navigation
* [Accompanist Adaptive](https://google.github.io/accompanist/adaptive/) for adaptive screen size support
* [Koin](https://insert-koin.io/) with [Koin Annotations](https://insert-koin.io/docs/setup/annotations/) for dependency injection
* Various other [AndroidX](https://developer.android.com/jetpack/androidx) and [Jetpack](https://developer.android.com/jetpack) libraries

## Recommended reading

* [Make apps fold aware (Official Android documentation)](https://developer.android.com/guide/topics/large-screens/make-apps-fold-aware)
* [Two Pane Layout with Accompanist Adaptive](https://devblogs.microsoft.com/surface-duo/jetpack-compose-accompanist-twopane/?ref=blog.ippon.tech)