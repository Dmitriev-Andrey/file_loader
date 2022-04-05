package args.converters;

import com.beust.jcommander.IStringConverter;
import url.URL;

public class UrlConverter implements IStringConverter<URL> {
    @Override
    public URL convert(String url) {
        return new URL(url);
    }
}
