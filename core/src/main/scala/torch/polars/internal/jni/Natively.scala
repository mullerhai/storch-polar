package torch.polars.internal.jni

import torch.polars.loadLibraryIfRequired

private[jni] trait Natively { loadLibraryIfRequired() }
