package ee.taltech.iti0202.exam.television;

import java.util.*;

public class Television {
    private boolean isOn = false;
    private int currentChannel = 1;
    private int volume = 50;
    private boolean isMuted = false;
    private final List<Integer> history = new ArrayList<>();
    private int historyIndex = -1;
    private final List<Integer> favoriteChannels = new ArrayList<>();

    /**
     * Turns the television on and adds channel 1 to history.
     */
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            goToChannel(1);
            historyIndex = 0;
        }
    }

    /**
     * Turns the television off and clears channel history. Favorite channels persist.
     */
    public void turnOff() {
        isOn = false;
        history.clear();
        historyIndex = -1;
    }

    /**
     * Changes the current channel to the given channel number.
     * Only works if the television is on. Adds the channel to history.
     * Throws IllegalArgumentException if the channel number is not greater than 0.
     *
     * @param channel the new channel number
     * @throws IllegalArgumentException if channel is less than or equal to 0
     */
    public void goToChannel(int channel) {
        if (!isOn || channel == currentChannel) return;
        if (channel <= 0) throw new IllegalArgumentException();
        history.add(channel);
        historyIndex = history.indexOf(channel);
        currentChannel = channel;
    }

    /**
     * Switches to the previous channel in the channel history if available.
     * Only works if the television is on. Adds the channel to history.
     */
    public void backChannel() {
        if (!isOn) return;
        if (historyIndex > 0) {
            historyIndex--;
            currentChannel = history.get(historyIndex);
            history.add(currentChannel);
        }
    }

    /**
     * Switches to the next channel in the channel history if available.
     * Only works if the television is on. Adds the channel to history.
     */
    public void forwardChannel() {
        if (!isOn || history.isEmpty()) return;
        if (historyIndex < history.size() - 1) {
            historyIndex++;
            currentChannel = history.get(historyIndex);
            history.add(currentChannel);
        }
    }

    /**
     * Adds a channel to the favorite channels list (max 5, no duplicates).
     * Only works if the television is on and channel is positive.
     *
     * @param channel the channel to add to favorites
     */
    public void addFavoriteChannel(int channel) {
        if (!isOn || channel <= 0 || favoriteChannels.contains(channel)) return;
        if (favoriteChannels.size() < 5) {
            favoriteChannels.add(channel);
        }
    }

    /**
     * Removes a channel from the favorite channels list.
     * Only works if the television is on and channel is positive.
     * Must use Integer.valueOf(channel) to remove the object, not the index, to avoid IndexOutOfBoundsException.
     *
     * @param channel the channel to remove from favorites
     */
    public void removeFavoriteChannel(int channel) {
        if (!isOn || channel <= 0) return;
        favoriteChannels.remove(Integer.valueOf(channel));
    }

    /**
     * Switches to the next favorite channel (cyclically).
     * If current channel is not in favorites, switches to the first favorite.
     * Only works if the television is on and favorites list is not empty.
     * Adds the channel to history.
     */
    public void nextFavoriteChannel() {
        if (!isOn || favoriteChannels.isEmpty()) return;
        int index = favoriteChannels.indexOf(currentChannel);
        if (index == -1) {
            goToChannel(favoriteChannels.getFirst());
        } else {
            goToChannel(favoriteChannels.get((index + 1) % favoriteChannels.size()));
        }
    }

    /**
     * Switches to the previous favorite channel (cyclically).
     * If current channel is not in favorites, switches to the last favorite.
     * Only works if the television is on and favorites list is not empty.
     * Adds the channel to history.
     */
    public void previousFavoriteChannel() {
        if (!isOn || favoriteChannels.isEmpty()) return;
        int index = favoriteChannels.indexOf(currentChannel);
        if (index == -1) {
            goToChannel(favoriteChannels.getLast());
        } else {
            goToChannel(favoriteChannels.get((index - 1 + favoriteChannels.size()) % favoriteChannels.size()));
        }
    }

    /**
     * Increases the volume by 25, up to a maximum of 100.
     * Only works if the television is on. Removes mute status if active.
     */
    public void increaseVolume() {
        if (!isOn) return;
        isMuted = false;
        volume = Math.min(100, volume + 25);
    }

    /**
     * Decreases the volume by 25, down to a minimum of 0.
     * Only works if the television is on. Removes mute status if active.
     */
    public void decreaseVolume() {
        if (!isOn) return;
        isMuted = false;
        volume = Math.max(0, volume - 25);
    }

    /**
     * Toggles mute on or off.
     * Only works if the television is on.
     */
    public void mute() {
        if (!isOn) return;
        isMuted = !isMuted;
    }

    /**
     * Returns the current volume (0 if muted, otherwise 0–100).
     *
     * @return the volume
     */
    public int getVolume() {
        return isMuted ? 0 : volume;
    }

    /**
     * Returns the current active channel.
     *
     * @return the current channel (default is 1)
     */
    public int getChannel() {
        return currentChannel;
    }

    /**
     * Returns a copy of the channel history list.
     *
     * @return list of channels in order of selection
     */
    public List<Integer> getChannelHistory() {
        return new ArrayList<>(history);
    }

    /**
     * Returns a copy of the favorite channels list.
     *
     * @return list of favorite channels
     */
    public List<Integer> getFavoriteChannels() {
        return new ArrayList<>(favoriteChannels);
    }

    /**
     * Checks whether the television is currently turned on.
     *
     * @return true if the television is on, false otherwise
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Checks whether the sound is currently muted.
     *
     * @return true if muted, false otherwise
     */
    public boolean isMuted() {
        return isMuted;
    }
}