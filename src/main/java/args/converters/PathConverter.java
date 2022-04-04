package args.converters;

import java.nio.file.Path;

import com.beust.jcommander.IStringConverter;

public class PathConverter implements IStringConverter<Path> {
    @Override
    public Path convert(String s) {
        return Path.of(s);
    }
}
