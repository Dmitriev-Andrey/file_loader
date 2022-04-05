/**
 * This package contains all loaders for different protocols.
 * To add a new loader:
 * 1. Add protocol in {@link url.Protocol}
 * 2. Create new loader for the protocol in this package. The loader must extend {@link loaders.Loader}
 * 3. Add new loader in {@link loaders.LoaderFactory}
 */
package loaders;
