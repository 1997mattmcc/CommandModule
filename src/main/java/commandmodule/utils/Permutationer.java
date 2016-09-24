package commandmodule.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.stream.IntStream;
import java.util.Objects;
import java.util.Arrays;

public class Permutationer {

    private final int[] blueprint;
    private final String[] words;

    public Permutationer(String[] words, int length) {
        Objects.requireNonNull(length < 1 ? null : length, String.format("Length is less than 1: %s", length));
        this.blueprint = new int[length];
        this.words = words;
    }

    public final boolean nextPermutation() {
        if (!(blueprint[blueprint.length - 1] == words.length)) {
            if (blueprint[blueprint.length - 1] > 0) {
                for (int i = blueprint.length - 2; i >= 0; i--) {
                    if (blueprint[i] > 0) {
                        blueprint[blueprint.length - 1]--;
                        blueprint[i]--;
                        blueprint[i + 1] += 2;
                        return true;
                    }
                }
            } else {
                for (int i = blueprint.length - 1; i >= 0; i--) {
                    if (blueprint[i] > 0) {
                        blueprint[i]--;
                        blueprint[i + 1]++;
                        return true;
                    }
                }
            }
        }
        return IntStream.of(blueprint).sum() == 0 ? this.setAsFirstPermutation() : false;
    }

    public final boolean nextPermutationToMatch(int[] lower, int[] upper) {
        if (!(blueprint.length == lower.length && blueprint.length == upper.length)) {
            throw new IllegalArgumentException(String.format("Array length mismatch: %s != %s or %s != %s", blueprint.length, lower.length, blueprint.length, upper.length));
        } else if (words.length < IntStream.of(lower).sum() || words.length > IntStream.of(upper).sum()) {
            this.setAsLastPermutation();
            return false;
        }
        return this.nextPermutationToMatchInternal(lower, upper);
    }

    public final boolean nextPermutationToMatchInternal(int[] lower, int[] upper) {
        if (this.nextPermutation()) {
            for (int i = 0; i < blueprint.length; i++) {
                if (blueprint[i] < lower[i] || blueprint[i] > upper[i]) {
                    return this.nextPermutationToMatch(lower, upper);
                } else if (i == blueprint.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean setAsFirstPermutation() {
        Arrays.fill(blueprint, 0);
        blueprint[0] += words.length;
        return true;
    }

    public final boolean setAsLastPermutation() {
        Arrays.fill(blueprint, 0);
        blueprint[blueprint.length - 1] += words.length;
        return true;
    }

    public final String[] buildPermutation() {
        for (int i = 0; i < words.length; i++) {
            words[i] = this.buildPermutation(i);
        }
        return words;
    }

    public final String buildPermutation(int index) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < index + 1; i++) {
            startIndex += i == 0 ? 0 : blueprint[i - 1];
            endIndex = startIndex + blueprint[i];
        }
        return StringUtils.join(words, " ", startIndex, endIndex);
    }
}
