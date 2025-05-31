package torch.polars.internal.jni.io

import torch.polars.internal.jni.Natively

private[polars] object scan extends Natively {

  @native def scanParquet(paths: Array[String], options: String): Long

  @native def scanIPC(paths: Array[String], options: String): Long

  @native def scanCSV(paths: Array[String], options: String): Long

  @native def scanJsonLines(paths: Array[String], options: String): Long

}
