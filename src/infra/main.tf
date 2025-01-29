provider "google" {
  project = "phonic-envoy-425900-q5"
  region  = "us-central1"
  zone    = "us-central1-a"
}

resource "google_bigtable_instance" "tracks" {
  name = "tracks"

  cluster {
    cluster_id   = "tracks"
    num_nodes    = 1
    storage_type = "HDD"
  }

  force_destroy       = true
  deletion_protection = false
}

resource "google_bigtable_table" "tracks" {
  name          = "tracks"
  instance_name = google_bigtable_instance.tracks.name
}
